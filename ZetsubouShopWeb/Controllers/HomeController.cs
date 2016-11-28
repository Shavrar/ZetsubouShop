using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using Microsoft.Ajax.Utilities;
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

        public async Task<ActionResult> Index()
        {
           return View();
        }

        public ActionResult Register()
        {
            return View();
        }
        [HttpPost]
        public async Task<ActionResult> Register(RegisterViewModel model)
        {
            if (ModelState.IsValid)
            {
                HttpResponseMessage responseMessage = await client.PostAsJsonAsync(url+"/api/Account/Register",model);
                if (responseMessage.IsSuccessStatusCode)
                {
                    return RedirectToAction("Login");
                }
            }
            return View(model);
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
                    //postData = new List< KeyValuePair < string, string>> ();
                    //.setRequestHeader("Authorization", "Bearer " + token);
                    client.DefaultRequestHeaders.Add("Authorization", "Bearer " + tokenResponse.AccessToken);
                    responseMessage = await client.GetAsync(url + "api/Account/UserData");
                    var data = JsonConvert.DeserializeObject<SessionStorage>(responseMessage.Content.ReadAsStringAsync().Result);
                    Session.Add("UserData", data);
                    client.DefaultRequestHeaders.Remove("Authorization");
                    return RedirectToAction("Index");
                }
                else
                {
                    ModelState.AddModelError("","Wrong login or password");
                }
            }
            return View(model);
        }

        public async Task<ActionResult> Logout()
        {
            //api/Account/Logout
            client.DefaultRequestHeaders.Add("Authorization", "Bearer " + ((TokenResponseModel)Session["token"]).AccessToken);
            await client.PostAsync(url+ "api/Account/Logout",null);
            Session.Remove("UserData");
            Session.Remove("token");
            return RedirectToAction("Index");
        }
    }
}