using EmpresaUTN.Modelos;
using EmpresaUTN.UniversalAPI;

/*
string baseUrl = "https://localhost:7285/api";

var uap = new Crud<Pais>();

var paises = uap.Select($"{baseUrl}/Pais");

//var ec = uap.SelectById($"{baseUrl}/Pais", "1");

//ec.Capital = "Quito DM";
//ec.Idioma = "ESPAÑOL";
//ec.Moneda = "DOLAR USD";

//uap.Update($"{baseUrl}/Pais", $"{ec.CodigoPais}", ec);

//var co = new Pais
//{
//    Nombre = "COLOMBIA",
//    Capital = "BOGOTA",
//    Idioma = "ESPAÑOL",
//    Moneda = "PESO COLOMBIANO",
//    Poblacion = 500000,
//    CodigoISO = "COL"
//};

//uap.Insert($"{baseUrl}/Pais", co);

uap.Delete($"{baseUrl}/Pais", "3");
*/

string baseUrl = "https://localhost:7285/api";

var api = new ClientCrud<Pais>(baseUrl);


//var paises = await api.Select("/Pais");
//foreach (var pais in paises!)
//{
//    Console.WriteLine($"{pais.Nombre} - {pais.Capital}");
//}
var ec = await api.SelectById("/Pais", "1");
//Console.WriteLine($"{ec?.Nombre} {ec?.Poblacion} {ec?.Capital}");

//ec!.Nombre = "Ecuador";
//ec!.Capital = "Distrito Metropolitano de Quito";
//ec!.Idioma = "Español";
//ec!.Moneda = "USD";

//await api.Update("/Pais", $"{ec.CodigoPais}", ec);

//var co = new Pais
//{
//    Nombre = "COLOMBIA",
//    Capital = "BOGOTA",
//    Idioma = "ESPAÑOL",
//    Moneda = "PESO COLOMBIANO",
//    Poblacion = 500000,
//    CodigoISO = "COL"
//};

//await api.Insert("/Pais", co);


//await api.Delete("/Pais", "4");