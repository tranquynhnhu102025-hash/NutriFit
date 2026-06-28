using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace BE.Models
{
    [Table("user")]
    public class User
    {
        [Key]
        public int Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }

        [JsonIgnore]
        public string Password { get; set; }

        [JsonIgnore]
        public ICollection<NutritionLog> NutritionLogs { get; set; }

        [JsonIgnore]
        public ICollection<WorkoutPlan> WorkoutPlans { get; set; }
    }
}