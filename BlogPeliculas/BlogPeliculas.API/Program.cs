using Microsoft.EntityFrameworkCore;
using System.Text.Json.Serialization;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddDbContext<BlogPeliculasContext>(options =>
    options.UseSqlite(builder.Configuration.GetConnectionString("BlogPeliculasContext") ?? throw new InvalidOperationException("Connection string 'BlogPeliculasContext' not found.")));

// Add services to the container.

builder.Services.AddControllers()
            .AddJsonOptions(options =>  // Ignore Reference Loop Handling
            options.JsonSerializerOptions.ReferenceHandler = ReferenceHandler.IgnoreCycles);

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddAutoMapper(typeof(Program));

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();