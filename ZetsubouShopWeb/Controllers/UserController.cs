using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web.Mvc;

namespace ZetsubouShopWeb.Controllers
{
    public class UserController : Controller
    {
        HttpClient client;
        string url = "http://localhost:3077/";
        public UserController()
        {
            client = new HttpClient();
            client.BaseAddress = new Uri(url);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        }
    }
}