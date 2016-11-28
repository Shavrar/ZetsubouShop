using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace ZetsubouShop.Helpers
{
    public static class JsonHelper
    {
        public static string ToJsonCamelCase(this object obj)
        {
            return JsonConvert.SerializeObject(obj, new JsonSerializerSettings { ContractResolver = new CamelCasePropertyNamesContractResolver() });
        }

        public static string ToJson(this object obj)
        {
            return JsonConvert.SerializeObject(obj);
        }

        public static T FromJson<T>(this string str)
        {
            if (string.IsNullOrWhiteSpace(str)) return default(T);
            return JsonConvert.DeserializeObject<T>(str);
        }
    }
}