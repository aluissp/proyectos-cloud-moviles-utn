using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace BlogPeliculas.API.Migrations
{
    /// <inheritdoc />
    public partial class AgregadoSeeder : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.InsertData(
                table: "Peliculas",
                columns: new[] { "Id", "EnCines", "FechaEstreno", "Titulo" },
                values: new object[,]
                {
                    { 1, true, new DateTime(2023, 4, 5, 0, 0, 0, 0, DateTimeKind.Unspecified), "Super Mario Bros.: La película" },
                    { 2, false, new DateTime(2022, 3, 25, 0, 0, 0, 0, DateTimeKind.Unspecified), "Doctor Strange: En el multiverso de la locura" },
                    { 3, true, new DateTime(2022, 3, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "The Batman" },
                    { 4, false, new DateTime(2023, 11, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "The Flash" },
                    { 5, true, new DateTime(2021, 10, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Venom: Habrá matanza" }
                });

            migrationBuilder.InsertData(
                table: "Comentarios",
                columns: new[] { "Id", "Contenido", "PeliculaId", "Recomendar" },
                values: new object[,]
                {
                    { 1, "¡Es una película muy divertida!", 1, true },
                    { 2, "¡Me encantó la trama!", 1, true },
                    { 3, "¡La volvere a ver!", 1, true },
                    { 4, "¡Asombroso!", 2, true },
                    { 5, "¡Me encantó!", 2, true },
                    { 6, "Me gusto los personajes pero no la trama.", 2, false },
                    { 7, "¡Es una película muy divertida!", 3, true },
                    { 8, "¡Me encantó la trama!", 3, true },
                    { 9, "¡Es una película muy divertida!", 5, true }
                });

            migrationBuilder.InsertData(
                table: "Personajes",
                columns: new[] { "Id", "Nombre", "PeliculaId" },
                values: new object[,]
                {
                    { 1, "Mario", 1 },
                    { 2, "Luigi", 1 },
                    { 3, "Peach", 1 },
                    { 4, "Bowser", 1 },
                    { 5, "Doctor Strange", 2 },
                    { 6, "Bruja Escarlata", 2 },
                    { 7, "Batman", 3 },
                    { 8, "Flash", 4 },
                    { 9, "Venom", 5 }
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Comentarios",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Comentarios",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Comentarios",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Comentarios",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "Comentarios",
                keyColumn: "Id",
                keyValue: 5);

            migrationBuilder.DeleteData(
                table: "Comentarios",
                keyColumn: "Id",
                keyValue: 6);

            migrationBuilder.DeleteData(
                table: "Comentarios",
                keyColumn: "Id",
                keyValue: 7);

            migrationBuilder.DeleteData(
                table: "Comentarios",
                keyColumn: "Id",
                keyValue: 8);

            migrationBuilder.DeleteData(
                table: "Comentarios",
                keyColumn: "Id",
                keyValue: 9);

            migrationBuilder.DeleteData(
                table: "Personajes",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Personajes",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Personajes",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Personajes",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "Personajes",
                keyColumn: "Id",
                keyValue: 5);

            migrationBuilder.DeleteData(
                table: "Personajes",
                keyColumn: "Id",
                keyValue: 6);

            migrationBuilder.DeleteData(
                table: "Personajes",
                keyColumn: "Id",
                keyValue: 7);

            migrationBuilder.DeleteData(
                table: "Personajes",
                keyColumn: "Id",
                keyValue: 8);

            migrationBuilder.DeleteData(
                table: "Personajes",
                keyColumn: "Id",
                keyValue: 9);

            migrationBuilder.DeleteData(
                table: "Peliculas",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Peliculas",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Peliculas",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Peliculas",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "Peliculas",
                keyColumn: "Id",
                keyValue: 5);
        }
    }
}
