namespace ZetsubouShop.Models
{
    public class Consts
    {
        public const string CustomerRole = "Customer";
        public const string AdministratorRole = "Administrator";
    }

    public enum UserType
    {
        None = 0,
        Customer = 100,
        Administrator = 200
    }

    public enum ItemType
    {
        None = 0,
        Vegetables = 100,
        Baking = 200,
        Drinks = 300,
        Meat = 400,
        Etc = 500
    }
}