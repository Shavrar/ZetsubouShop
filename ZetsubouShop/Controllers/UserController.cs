using System;
using System.Collections;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Cors;
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

        public void Post([FromBody]UserViewModel model)
        {
            model.Id = Guid.NewGuid();
            var user = new ApplicationUser
            {
                Id = model.Id,
                Email = model.Email,
                FirstName = model.FirstName,
                LastName = model.LastName,
                UserName = model.UserName
            };
            _userManager.CreateAsync(user, model.Password ?? "12345").RunSynchronously();
            if (model.Type == UserType.Administrator)
            {
                _userManager.AddToRoleAsync(model.Id, Consts.AdministratorRole).RunSynchronously();
            }
            else
            {
                _userManager.AddToRoleAsync(model.Id, Consts.CustomerRole).RunSynchronously();
            }
        }

        // PUT api/values/5
        public void Put(Guid id, [FromBody]Item item)
        {
            if (id == item.Id)
            {
                _db.Entry(item).State = EntityState.Modified;

                _db.SaveChanges();
            }
        }

        // DELETE api/values/5
        public void Delete(Guid id)
        {
           _userManager.re
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