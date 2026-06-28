using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace BE.Migrations
{
    /// <inheritdoc />
    public partial class AddForeignKeys : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_workout_plan_user_id",
                table: "workout_plan",
                column: "user_id");

            migrationBuilder.CreateIndex(
                name: "IX_nutrition_log_user_id",
                table: "nutrition_log",
                column: "user_id");

            migrationBuilder.AddForeignKey(
                name: "FK_nutrition_log_user_user_id",
                table: "nutrition_log",
                column: "user_id",
                principalTable: "user",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_workout_plan_user_user_id",
                table: "workout_plan",
                column: "user_id",
                principalTable: "user",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_nutrition_log_user_user_id",
                table: "nutrition_log");

            migrationBuilder.DropForeignKey(
                name: "FK_workout_plan_user_user_id",
                table: "workout_plan");

            migrationBuilder.DropIndex(
                name: "IX_workout_plan_user_id",
                table: "workout_plan");

            migrationBuilder.DropIndex(
                name: "IX_nutrition_log_user_id",
                table: "nutrition_log");
        }
    }
}
