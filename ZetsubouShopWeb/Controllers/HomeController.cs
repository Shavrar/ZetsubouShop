using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using Newtonsoft.Json;
using ZetsubouShopWeb.ViewModels;

namespace ZetsubouShopWeb.Controllers
{
    public class HomeController : Controller
    {
        HttpClient client;
        string url = "http://localhost:3077/";
        public HomeController()
        {
            client = new HttpClient();
            client.BaseAddress = new Uri(url);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Register()
        {
            return View();
        }

        public ActionResult Test()
        {
            return View();
        }

        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public async Task<ActionResult> Login(LoginViewModel model)
        {
            /*
             * grant_type: 'password',
                username: $('#emailLogin').val(),
                password: $('#passwordLogin').val()
             * */
            if (ModelState.IsValid)
            {
                var postData = new List<KeyValuePair<string, string>>();
                postData.Add(new KeyValuePair<string, string>("grant_type", "password"));
                postData.Add(new KeyValuePair<string, string>("username", model.Email));
                postData.Add(new KeyValuePair<string, string>("password", model.Password));
                HttpContent content = new FormUrlEncodedContent(postData);
                HttpResponseMessage responseMessage = await client.PostAsync("http://localhost:3077/Token", content);
                if (responseMessage.IsSuccessStatusCode)
                {
                    var responseData = responseMessage.Content.ReadAsStringAsync().Result;
                    var tokenResponse = JsonConvert.DeserializeObject<TokenResponseModel>(responseData);
                    Session.Add("token",tokenResponse);                    
                    return RedirectToAction("Index");
                }
                else
                {
                    
                }
            }
            return View(model);
        }
    }
}