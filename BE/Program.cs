using Microsoft.EntityFrameworkCore;
using BE.Data;
using BE.Models;

var builder = WebApplication.CreateBuilder(args);

var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");
builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString)));

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddCors(options =>
{
    options.AddDefaultPolicy(policy =>
        policy.AllowAnyOrigin().AllowAnyMethod().AllowAnyHeader());
});

var app = builder.Build();

app.UseCors();
app.UseSwagger();
app.UseSwaggerUI();
app.UseHttpsRedirection();

// ===== USER APIs =====

app.MapGet("/api/users", async (AppDbContext db) =>
{
    var users = await db.user.ToListAsync();
    return Results.Ok(users);
});

app.MapPost("/api/users", async (User newUser, AppDbContext db) =>
{
    db.user.Add(newUser);
    await db.SaveChangesAsync();
    return Results.Created($"/api/users/{newUser.Id}", newUser);
});

// ===== LOGIN API =====

app.MapPost("/login", async (LoginRequest request, AppDbContext db) =>
{
    var user = await db.user
        .FirstOrDefaultAsync(u => u.Email == request.Email && u.Password == request.Password);

    if (user == null)
        return Results.Json(new { success = false, message = "Sai email hoặc mật khẩu" }, statusCode: 401);

    return Results.Json(new
    {
        success = true,
        user = new { user.Id, user.Name, user.Email }
    });
});

// ===== FOOD DICTIONARY APIs =====

app.MapGet("/food_dictionary", async (AppDbContext db) =>
{
    var foods = await db.food_dictionary.ToListAsync();
    return Results.Ok(foods.Select(f => new
    {
        id = f.Id,
        food_name = f.FoodName,
        calories = f.Calories
    }));
});

app.MapPost("/food_dictionary", async (FoodRequest request, AppDbContext db) =>
{
    var food = new FoodDictionary
    {
        FoodName = request.FoodName,
        Calories = request.Calories
    };
    db.food_dictionary.Add(food);
    await db.SaveChangesAsync();
    return Results.Created($"/food_dictionary/{food.Id}", new
    {
        id = food.Id,
        food_name = food.FoodName,
        calories = food.Calories
    });
});

// ===== NUTRITION LOG APIs =====

app.MapGet("/nutrition_log", async (AppDbContext db) =>
{
    var logs = await db.nutrition_log.ToListAsync();
    return Results.Ok(logs.Select(l => new
    {
        id = l.Id,
        user_id = l.UserId,
        food_name = l.FoodName,
        calories = l.Calories,
        log_date = l.LogDate.ToString("yyyy-MM-dd HH:mm:ss")
    }));
});

app.MapPost("/nutrition_log", async (NutritionLogRequest request, AppDbContext db) =>
{
    var log = new NutritionLog
    {
        UserId = request.UserId,
        FoodName = request.FoodName,
        Calories = request.Calories,
        LogDate = request.LogDate
    };
    db.nutrition_log.Add(log);
    await db.SaveChangesAsync();
    return Results.Created($"/nutrition_log/{log.Id}", new
    {
        id = log.Id,
        user_id = log.UserId,
        food_name = log.FoodName,
        calories = log.Calories,
        log_date = log.LogDate.ToString("yyyy-MM-dd HH:mm:ss")
    });
});

// ===== WORKOUT PLAN APIs =====

app.MapGet("/workout_plan", async (AppDbContext db) =>
{
    var plans = await db.workout_plan.ToListAsync();
    return Results.Ok(plans.Select(w => new
    {
        id = w.Id,
        user_id = w.UserId,
        exercise_name = w.ExerciseName,
        duration = w.Duration,
        plan_date = w.PlanDate.ToString("yyyy-MM-dd HH:mm:ss")
    }));
});

app.MapPost("/workout_plan", async (WorkoutPlanRequest request, AppDbContext db) =>
{
    var plan = new WorkoutPlan
    {
        UserId = request.UserId,
        ExerciseName = request.ExerciseName,
        Duration = request.Duration,
        PlanDate = request.PlanDate
    };
    db.workout_plan.Add(plan);
    await db.SaveChangesAsync();
    return Results.Created($"/workout_plan/{plan.Id}", new
    {
        id = plan.Id,
        user_id = plan.UserId,
        exercise_name = plan.ExerciseName,
        duration = plan.Duration,
        plan_date = plan.PlanDate.ToString("yyyy-MM-dd HH:mm:ss")
    });
});

app.Run();

// ===== REQUEST DTOs =====

record LoginRequest(string Email, string Password);
record FoodRequest(string FoodName, int Calories);
record NutritionLogRequest(int UserId, string FoodName, int Calories, DateTime LogDate);
record WorkoutPlanRequest(int UserId, string ExerciseName, int Duration, DateTime PlanDate);
