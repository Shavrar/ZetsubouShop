using System;
using System.Collections;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net.Http;
using System.Web.Http;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin.Security;
using ZetsubouShop.Models;

namespace ZetsubouShop.Controllers
{
    [Authorize]
    [RoutePrefix("api/User")]
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
            
            return UserManager.Users.Select(a => new UserViewModel
            {
                Email = a.Email,
                FirstName = a.FirstName,
                Id = a.Id,
                LastName = a.LastName,
                UserName = a.UserName,
                Type = UserManager.GetRolesAsync(a.Id).Result.FirstOrDefault() == Consts.AdministratorRole ? UserType.Administrator : UserType.Customer 
            }).ToList();
        }

        public UserViewModel Get(Guid id)
        {
            if (!User.IsInRole(Consts.AdministratorRole))
            {
                return null;
            }
            return UserManager.Users.Where(a => a.Id == id).Select(a => new UserViewModel
            {
                Email = a.Email,
                FirstName = a.FirstName,
                Id = a.Id,
                LastName = a.LastName,
                UserName = a.UserName,
                Type =
                    UserManager.GetRolesAsync(a.Id).Result.FirstOrDefault() == Consts.AdministratorRole
                        ? UserType.Administrator
                        : UserType.Customer
            }).FirstOrDefault();
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