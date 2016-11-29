using System;
using System.Collections;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Cors;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin.Security;
using ZetsubouShop.Models;

namespace ZetsubouShop.Controllers
{
    [Authorize]
    public class UserController : ApiController
    {
        private const string LocalLoginProvider = "Local";
        private ApplicationUserManager _userManager;

        public UserController()
        {
        }

        public UserController(ApplicationUserManager userManager,
            ISecureDataFormat<AuthenticationTicket> accessTokenFormat)
        {
            UserManager = userManager;
            AccessTokenFormat = accessTokenFormat;
        }

        public ApplicationUserManager UserManager
        {
            get
            {
                return _userManager ?? Request.GetOwinContext().GetUserManager<ApplicationUserManager>();
            }
            private set
            {
                _userManager = value;
            }
        }

        public ISecureDataFormat<AuthenticationTicket> AccessTokenFormat { get; private set; }

        public IEnumerable<UserViewModel> Get()
        {
            if (!User.IsInRole(Consts.AdministratorRole))
            {
                return null;
            }
            
            var users = UserManager.Users.Select(a => new UserViewModel
            {
                Email = a.Email,
                FirstName = a.FirstName,
                Id = a.Id,
                LastName = a.LastName,
                UserName = a.UserName
            }).ToList();
            foreach (var user in users)
            {
                user.Type = UserManager.GetRolesAsync(user.Id).Result.FirstOrDefault() == Consts.AdministratorRole
                    ? UserType.Administrator
                    : UserType.Customer;
            }
            return users;
        }

        public UserViewModel Get(Guid id)
        {
            if (!User.IsInRole(Consts.AdministratorRole))
            {
                return null;
            }
            var user = UserManager.Users.Where(a => a.Id == id).Select(a => new UserViewModel
            {
                Email = a.Email,
                FirstName = a.FirstName,
                Id = a.Id,
                LastName = a.LastName,
                UserName = a.UserName
            }).FirstOrDefault();
            user.Type = UserManager.GetRolesAsync(user.Id).Result.FirstOrDefault() == Consts.AdministratorRole
                ? UserType.Administrator
                : UserType.Customer;
            return user;
        }

        public async Task<HttpResponseMessage> Post([FromBody]UserViewModel model)
        {
            var user = new ApplicationUser
            {
                Email = model.Email,
                FirstName = model.FirstName,
                LastName = model.LastName,
                UserName = model.UserName,
                Id = Guid.NewGuid(),
            };
            var result = await UserManager.CreateAsync(user, !string.IsNullOrEmpty(model.Password) ? model.Password : "!Aa12345");
            if (!result.Succeeded)
            {
                return new HttpResponseMessage(HttpStatusCode.BadRequest);
            }
            if (model.Type == UserType.Administrator)
            {
                await UserManager.AddToRoleAsync(user.Id, Consts.AdministratorRole);
            }
            else
            {
                await UserManager.AddToRoleAsync(user.Id, Consts.CustomerRole);
            }
            return new HttpResponseMessage(HttpStatusCode.Created);
        }

        // PUT api/values/5
        public HttpResponseMessage Put(Guid id, [FromBody]UserViewModel model)
        {
            var user = UserManager.FindById(id);
            if (id == model.Id)
            {
                user.UserName = model.UserName;
                user.FirstName = model.FirstName;
                user.LastName = model.LastName;
                user.Email = model.Email;
                UserManager.Update(user);
                if(!string.IsNullOrEmpty(model.Password))
                {
                    UserManager.RemovePassword(id);
                    UserManager.AddPassword(id,model.Password);
                }
                var role = UserManager.GetRoles(id).FirstOrDefault();
                if (model.Type == UserType.Administrator && role == Consts.CustomerRole)
                {
                    UserManager.RemoveFromRole(id, Consts.CustomerRole);
                    UserManager.AddToRole(id, Consts.AdministratorRole);
                }
                else if(model.Type == UserType.Customer && role == Consts.AdministratorRole)
                {
                    UserManager.RemoveFromRole(id, Consts.AdministratorRole);
                    UserManager.AddToRole(id, Consts.CustomerRole);
                }
            }
            return new HttpResponseMessage(HttpStatusCode.NoContent);
        }

        // DELETE api/values/5
        public HttpResponseMessage Delete(Guid id)
        {
            var user = UserManager.FindById(id);
            var result = UserManager.Delete(user);
            if (result.Succeeded)
            {
                return new HttpResponseMessage(HttpStatusCode.NoContent);
            }
            return new HttpResponseMessage(HttpStatusCode.BadRequest);
        }


        protected override void Dispose(bool disposing)
        {
            if (disposing && _userManager != null)
            {
                _userManager.Dispose();
                _userManager = null;
            }

            base.Dispose(disposing);
        }
    }
}