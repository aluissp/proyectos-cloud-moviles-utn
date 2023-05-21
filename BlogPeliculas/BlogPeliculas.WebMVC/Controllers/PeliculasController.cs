using BlogPeliculas.Modelos;
using BlogPeliculas.UniversalAPI;
using Microsoft.AspNetCore.Mvc;

namespace BlogPeliculas.WebMVC.Controllers
{
    public class PeliculasController : Controller
    {
        private readonly ClientCrud<Pelicula> peliculaApi = new("https://localhost:7093/api");

        // GET: PeliculasController
        public async Task<ActionResult> Index()
        {
            var peliculas = await peliculaApi.Select("/Peliculas");
            return View(peliculas);
        }

        // GET: PeliculasController/Details/5
        public ActionResult Details(int id)
        {
            var pelicula = peliculaApi.SelectById("/Peliculas", id.ToString());
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
        public ActionResult Delete(int id)
        {
            var pelicula = peliculaApi.SelectById("/Peliculas", id.ToString());
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
