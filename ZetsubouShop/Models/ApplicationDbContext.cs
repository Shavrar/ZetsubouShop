using System;
using System.Data.Entity;
using Microsoft.AspNet.Identity.EntityFramework;

namespace ZetsubouShop.Models
{
    public class ApplicationDbContext : IdentityDbContext<ApplicationUser, Role, Guid, UserLogin, UserRole, UserClaim>
    {
        public DbSet<UserRole> UserRoles { get; set; }
        public DbSet<UserClaim> UserClaims { get; set; }
        public DbSet<Item> Items { get; set; }
        public DbSet<UserItem> UserItems { get; set; }

        public ApplicationDbContext() : base("DefaultConnection")
        {
            Configuration.ProxyCreationEnabled = false;
        }

        public static ApplicationDbContext Create()
        {
            return new ApplicationDbContext();
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<ApplicationUser>().ToTable("Users");
            modelBuilder.Entity<Role>().ToTable("Roles");
            modelBuilder.Entity<UserRole>().ToTable("UserRoles");
            modelBuilder.Entity<UserClaim>().ToTable("UserClaims");
            modelBuilder.Entity<UserLogin>().ToTable("UserLogins");

            modelBuilder.Entity<Item>().HasKey(i => i.Id);
            modelBuilder.Entity<UserItem>().HasKey(ui => new { ui.UserId, ui.ItemId });
            modelBuilder.Entity<UserItem>().HasRequired(ui => ui.User).WithMany(u => u.UserItems).HasForeignKey(ui => ui.UserId);
            modelBuilder.Entity<UserItem>().HasRequired(ui => ui.Item).WithMany(i => i.ItemUsers).HasForeignKey(ui => ui.ItemId);
        }
    }
}