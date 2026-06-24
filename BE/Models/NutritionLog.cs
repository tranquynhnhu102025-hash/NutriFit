using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BE.Models
{
    [Table("nutrition_log")]
    public class NutritionLog
    {
        [Key]
        public int Id { get; set; }

        [Column("user_id")]
        public int UserId { get; set; }

        [Column("food_name")]
        public string FoodName { get; set; }

        [Column("calories")]
        public int Calories { get; set; }

        [Column("log_date")]
        public DateTime LogDate { get; set; }
    }
}
