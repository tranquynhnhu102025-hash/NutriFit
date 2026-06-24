using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BE.Models
{
    [Table("workout_plan")]
    public class WorkoutPlan
    {
        [Key]
        public int Id { get; set; }

        [Column("user_id")]
        public int UserId { get; set; }

        [Column("exercise_name")]
        public string ExerciseName { get; set; }

        [Column("duration")]
        public int Duration { get; set; }

        [Column("plan_date")]
        public DateTime PlanDate { get; set; }
    }
}
