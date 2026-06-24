using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BE.Models
{
    [Table("food_dictionary")]
    public class FoodDictionary
    {
        [Key]
        public int Id { get; set; }

        [Column("food_name")]
        public string FoodName { get; set; }

        [Column("calories")]
        public int Calories { get; set; }
    }
}
