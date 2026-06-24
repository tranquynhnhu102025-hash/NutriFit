using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using BE.Data;

namespace BE.Data
{
    public class AppDbContextFactory : IDesignTimeDbContextFactory<AppDbContext>
    {
        public AppDbContext CreateDbContext(string[] args)
        {
            var optionsBuilder = new DbContextOptionsBuilder<AppDbContext>();

           var connectionString = "server=localhost;database=NutriFitDB;user=root;password=123456";

            optionsBuilder.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString));

            return new AppDbContext(optionsBuilder.Options);
        }
    }
}