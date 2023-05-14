using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace EmpresaUTN.API.Migrations
{
    /// <inheritdoc />
    public partial class agregadoEntidadesYSeeding : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Paises",
                columns: table => new
                {
                    CodigoPais = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Nombre = table.Column<string>(type: "TEXT", nullable: false),
                    Poblacion = table.Column<int>(type: "INTEGER", nullable: false),
                    CodigoISO = table.Column<string>(type: "TEXT", nullable: false),
                    Moneda = table.Column<string>(type: "TEXT", nullable: false),
                    Capital = table.Column<string>(type: "TEXT", nullable: false),
                    Idioma = table.Column<string>(type: "TEXT", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Paises", x => x.CodigoPais);
                });

            migrationBuilder.CreateTable(
                name: "Provincias",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Nombre = table.Column<string>(type: "TEXT", nullable: false),
                    Area = table.Column<int>(type: "INTEGER", nullable: false),
                    ActividadEconomica = table.Column<string>(type: "TEXT", nullable: false),
                    PaisCodigoPais = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Provincias", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Provincias_Paises_PaisCodigoPais",
                        column: x => x.PaisCodigoPais,
                        principalTable: "Paises",
                        principalColumn: "CodigoPais",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Cantones",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Nombre = table.Column<string>(type: "TEXT", nullable: false),
                    CabeceraCantonal = table.Column<string>(type: "TEXT", nullable: false),
                    ProvinciaId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Cantones", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Cantones_Provincias_ProvinciaId",
                        column: x => x.ProvinciaId,
                        principalTable: "Provincias",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.InsertData(
                table: "Paises",
                columns: new[] { "CodigoPais", "Capital", "CodigoISO", "Idioma", "Moneda", "Nombre", "Poblacion" },
                values: new object[,]
                {
                    { 1, "Quito", "EC", "Español", "USD", "Ecuador", 17643054 },
                    { 2, "Buenos Aires", "AR", "Español", "ARS", "Argentina", 44938712 }
                });

            migrationBuilder.InsertData(
                table: "Provincias",
                columns: new[] { "Id", "ActividadEconomica", "Area", "Nombre", "PaisCodigoPais" },
                values: new object[,]
                {
                    { 1, "Agricultura", 10487, "Pichincha", 1 },
                    { 2, "Pesca", 15480, "Guayas", 1 },
                    { 3, "Ganaderia", 3800, "Carchi", 1 },
                    { 4, "Turismo", 4600, "Imbabura", 1 },
                    { 5, "Agricultura", 307571, "Buenos Aires", 2 }
                });

            migrationBuilder.InsertData(
                table: "Cantones",
                columns: new[] { "Id", "CabeceraCantonal", "Nombre", "ProvinciaId" },
                values: new object[,]
                {
                    { 1, "Distrito Metropolitano de Quito", "Quito", 1 },
                    { 2, "Guayaquil", "Guayaquil", 2 },
                    { 3, "Tulcan", "Tulcan", 3 },
                    { 4, "Ibarra", "Ibarra", 4 },
                    { 5, "La Libertad", "La Libertad", 5 },
                    { 6, "Otavalo", "Otavalo", 4 },
                    { 7, "Tabacundo", "Pedro Moncayo", 1 },
                    { 8, "Quito", "Quito Sur", 1 },
                    { 9, "Cotacachi", "Cotacachi", 4 }
                });

            migrationBuilder.CreateIndex(
                name: "IX_Cantones_ProvinciaId",
                table: "Cantones",
                column: "ProvinciaId");

            migrationBuilder.CreateIndex(
                name: "IX_Provincias_PaisCodigoPais",
                table: "Provincias",
                column: "PaisCodigoPais");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Cantones");

            migrationBuilder.DropTable(
                name: "Provincias");

            migrationBuilder.DropTable(
                name: "Paises");
        }
    }
}
