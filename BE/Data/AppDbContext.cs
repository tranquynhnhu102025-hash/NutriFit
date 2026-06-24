using Microsoft.EntityFrameworkCore;
using BE.Models;

namespace BE.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }
        public DbSet<User> user { get; set; }
        public DbSet<FoodDictionary> food_dictionary { get; set; }
        public DbSet<NutritionLog> nutrition_log { get; set; }
        public DbSet<WorkoutPlan> workout_plan { get; set; }
    }
}