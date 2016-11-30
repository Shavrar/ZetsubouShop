namespace ZetsubouShop.Models
{
    public class UserFilter
    {
        public string UserName { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public UserType Type { get; set; }

    }

    public class ItemFilter
    {
        public string Name { get; set; }
        public string Description { get; set; }
        public double Price { get; set; }
        public ItemType Type { get; set; }
    }
}