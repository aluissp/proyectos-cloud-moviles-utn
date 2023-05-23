using BlogPeliculas.Modelos;
using BlogPeliculas.UniversalAPI;
using Microsoft.AspNetCore.Mvc;

namespace BlogPeliculas.WebMVC.Controllers
{
    public class PeliculasController : Controller
    {
        private readonly ClientCrud<Pelicula> peliculaApi;
        private readonly ClientCrud<Personaje> personajeApi;
        private readonly ClientCrud<Comentario> comentarioApi;

        public PeliculasController(IConfiguration configuration)
        {
            var apiUrl = configuration.GetValue<string>("ApiUrl");
            peliculaApi = new(apiUrl!);
            personajeApi = new(apiUrl!);
            comentarioApi = new(apiUrl!);
        }

        // GET: PeliculasController
        public async Task<ActionResult> Index()
        {
            var peliculas = await peliculaApi.Select("/Peliculas");
            return View(peliculas);
        }

        // GET: PeliculasController/Details/5
        public async Task<ActionResult> Details(int id)
        {
            var pelicula = await peliculaApi.SelectById("/Peliculas", id.ToString());
            var personajes = (await personajeApi.Select("/Personajes"))
                    ?.Where(p => p.PeliculaId == id)
                    .ToList();

            var comentarios = (await comentarioApi.Select("/Comentarios"))
                    ?.Where(c => c.PeliculaId == id)
                    .ToList();

            ViewBag.Personajes = personajes;
            ViewBag.Comentarios = comentarios;

            return View(pelicula);
        }

        // GET: PeliculasController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: PeliculasController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Create(Pelicula pelicula)
        {
            try
            {
                await peliculaApi.Insert("/Peliculas", pelicula);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(pelicula);
            }
        }

        // GET: PeliculasController/Edit/5
        public async Task<ActionResult> Edit(int id)
        {
            var pelicula = await peliculaApi.SelectById("/Peliculas", id.ToString());
            return View(pelicula);
        }

        // POST: PeliculasController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Edit(int id, Pelicula pelicula)
        {
            try
            {
                await peliculaApi.Update("/Peliculas", id.ToString(), pelicula);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(pelicula);
            }
        }

        // GET: PeliculasController/Delete/5
        public async Task<ActionResult> Delete(int id)
        {
            var pelicula = await peliculaApi.SelectById("/Peliculas", id.ToString());
            return View(pelicula);
        }

        // POST: PeliculasController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Delete(int id, Pelicula pelicula)
        {
            try
            {
                await peliculaApi.Delete("/Peliculas", id.ToString());
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(pelicula);
            }
        }
    }
}
