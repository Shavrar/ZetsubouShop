using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Microsoft.AspNet.Identity;
using ZetsubouShop.Models;

namespace ZetsubouShop.Controllers
{
    [Authorize]
    public class ShopController : ApiController
    {
        private readonly ApplicationDbContext _db = new ApplicationDbContext();

        public IEnumerable<UserItemViewModel> Get()
        {
            var userId = Guid.Parse(User.Identity.GetUserId());
            return _db.UserItems.Where(a => a.UserId == userId).Include(a => a.Item).Select(a => new UserItemViewModel
            {
                Amount = a.Amount,
                Description = a.Item.Description,
                Id = a.ItemId,
                Name = a.Item.Name,
                Price = a.Item.Price,
                Type = a.Item.Type
            }).ToList();
        }

        public HttpResponseMessage Post(Guid id)
        {
            var userId = Guid.Parse(User.Identity.GetUserId());
            if (_db.UserItems.Any(a => a.UserId == userId && a.ItemId == id))
            {
                var ui = _db.UserItems.FirstOrDefault(a => a.UserId == userId && a.ItemId == id);
                ui.Amount += 1;
                _db.Entry(ui).State = EntityState.Modified;
            }
            else
            {
                _db.UserItems.Add(new UserItem {Amount = 1, ItemId = id, UserId = userId});
            }
            _db.SaveChanges();
            return new HttpResponseMessage(HttpStatusCode.Created);
        }


        // DELETE api/values/5
        public HttpResponseMessage Delete(Guid id)
        {
            var userId = Guid.Parse(User.Identity.GetUserId());
            var to_remove = _db.UserItems.Where(a => a.UserId == userId && a.ItemId == id);
            _db.UserItems.RemoveRange(to_remove);
            _db.SaveChanges();
            return new HttpResponseMessage(HttpStatusCode.NoContent);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                _db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}