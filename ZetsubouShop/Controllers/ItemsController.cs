﻿using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;
using ZetsubouShop.Models;

namespace ZetsubouShop.Controllers
{
    //[EnableCors(origins: "*", headers: "*", methods: "*")]
    [Authorize]
    public class ItemsController : ApiController
    {
        private readonly ApplicationDbContext _db = new ApplicationDbContext();
        [AllowAnonymous]
        // GET api/values
        public IEnumerable<Item> Get([FromUri]ItemFilter filter)
        {
            var query = _db.Items.ToList();
            if (!string.IsNullOrEmpty(filter.Name))
            {
                query = query.Where(a => a.Name.Contains(filter.Name)).ToList();
            }
            if (!string.IsNullOrEmpty(filter.Description))
            {
                query = query.Where(a => a.Description.Contains(filter.Description)).ToList();
            }
            if (filter.Price > 0)
            {
                query = query.Where(a => a.Price < filter.Price).ToList();
            }
            if (filter.Type!= ItemType.None)
            {
                query = query.Where(a => a.Type == filter.Type).ToList();
            }
            return query;
        }
        [AllowAnonymous]
        // GET api/values/5
        public Item Get(Guid id)
        {
            return _db.Items.FirstOrDefault(a => a.Id == id);
        }

        // POST api/values
        public void Post([FromBody]Item item)
        {
            item.Id = Guid.NewGuid();
            _db.Items.Add(item);
            _db.SaveChanges();
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
            var item = _db.Items.FirstOrDefault(a => a.Id == id);
            if (item != null)
            {
                _db.Items.Remove(item);
                _db.SaveChanges();
            }
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
