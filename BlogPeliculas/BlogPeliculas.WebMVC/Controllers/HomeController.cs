using BlogPeliculas.Modelos;
using BlogPeliculas.UniversalAPI;
using BlogPeliculas.WebMVC.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace BlogPeliculas.WebMVC.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private readonly ClientCrud<Pelicula> peliculasApi;


        public HomeController(ILogger<HomeController> logger, IConfiguration configuration)
        {
            var apiUrl = configuration.GetValue<string>("ApiUrl");
            peliculasApi = new ClientCrud<Pelicula>(apiUrl!);

            _logger = logger;
        }

        public async Task<IActionResult> Index()
        {
            var peliculas = await peliculasApi.Select("/Peliculas");


            var numPeliculas = peliculas?.Length;
            var numComentarios = peliculas?.Sum(p => p.Comentarios.Count);
            var numPersonajes = peliculas?.Sum(p => p.Personjes.Count);

            ViewBag.TotalPeliculas = numPeliculas;
            ViewBag.TotalComentarios = numComentarios;
            ViewBag.TotalPersonajes = numPersonajes;

            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}