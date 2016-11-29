using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web.Mvc;
using Newtonsoft.Json;
using ZetsubouShopWeb.ViewModels;

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

        public ActionResult Index()
        {
            if (Session["token"] == null || ((SessionStorage)Session["UserData"]).Role != "Administrator")
            {
                return View("Error");
            }
            return View();
        }

        public ActionResult Create()
        {
            if (Session["token"] == null || ((SessionStorage)Session["UserData"]).Role != "Administrator")
            {
                return View("Error");
            }
            return View("Edit", new UserViewModel());
        }

        public ActionResult Edit(Guid id)
        {
            if (Session["token"] == null || ((SessionStorage)Session["UserData"]).Role != "Administrator")
            {
                return View("Error");
            }
            client.DefaultRequestHeaders.Add("Authorization", "Bearer " + ((TokenResponseModel)Session["token"]).AccessToken);
            var response = client.GetAsync(url + "api/User/" + id.ToString()).Result;
            client.DefaultRequestHeaders.Remove("Authorization");
            var model = JsonConvert.DeserializeObject<UserViewModel>(response.Content.ReadAsStringAsync().Result);
            return View(model);
        }
        [HttpPost]
        public ActionResult Edit(UserViewModel model)
        {
            if (Session["token"] == null || ((SessionStorage)Session["UserData"]).Role != "Administrator")
            {
                return View("Error");
            }
            if (ModelState.IsValid)
            {
                HttpResponseMessage response;
                client.DefaultRequestHeaders.Add("Authorization", "Bearer " + ((TokenResponseModel)Session["token"]).AccessToken);
                if (model.Id != Guid.Empty)
                {
                    response = client.PutAsJsonAsync(url + "api/User/" + model.Id.ToString(), model).Result;
                }
                else
                {
                    response = client.PostAsJsonAsync(url + "api/User", model).Result;
                }
                client.DefaultRequestHeaders.Remove("Authorization");
                if (response.IsSuccessStatusCode)
                {
                    return RedirectToAction("Index", "User");
                }
                else
                {
                    ModelState.AddModelError("", "Waaaaaaaaaaarh");
                }
            }
            return View(model);
        }

        public ActionResult Remove(Guid id)
        {
            if (Session["token"] == null || ((SessionStorage)Session["UserData"]).Role != "Administrator")
            {
                return View("Error");
            }
            client.DefaultRequestHeaders.Add("Authorization", "Bearer " + ((TokenResponseModel)Session["token"]).AccessToken);
            var response = client.DeleteAsync(url + "api/User/" + id.ToString()).Result;
            client.DefaultRequestHeaders.Remove("Authorization");
            return RedirectToAction("Index", "User");
        }
    }
}