using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web.Mvc;
using ZetsubouShopWeb.ViewModels;

namespace ZetsubouShopWeb.Controllers
{
    public class ShopController: Controller
    {
        public ActionResult Index()
        {
            return View();
        }
    }
}