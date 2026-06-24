using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace BE.Models
{
    [Table("user")]
    public class User
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }

        [JsonIgnore]
        public string Password { get; set; }
    }
}