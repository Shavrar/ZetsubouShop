using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace ZetsubouShopWeb.ViewModels
{
    public class Item
    {
        public Guid Id { get; set; }
        public string Description { get; set; }
        public double Price { get; set; }
        public string Name { get; set; }
        public ItemType Type { get; set; }
        public int Count { get; set; }
    }

    public class ItemViewModel
    {

        public Guid Id { get; set; }
        [Required]
        public string Description { get; set; }
        [Required]
        public double Price { get; set; }
        [Required]
        public string Name { get; set; }
        [Required]
        public ItemType Type { get; set; }
        [Required]
        public int Count { get; set; }
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