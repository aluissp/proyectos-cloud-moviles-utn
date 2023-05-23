using BlogPeliculas.Modelos;
using BlogPeliculas.UniversalAPI;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace BlogPeliculas.WebMVC.Controllers
{
    public class PersonajesController : Controller
    {
        private readonly ClientCrud<Personaje> personajeApi;
        private readonly ClientCrud<Pelicula> peliculaApi;

        public PersonajesController(IConfiguration configuration)
        {
            var apiUrl = configuration.GetValue<string>("ApiUrl");
            personajeApi = new(apiUrl!);
            peliculaApi = new(apiUrl!);
        }

        // GET: PersonajesController
        public async Task<ActionResult> Index()
        {
            var personajes = await personajeApi.Select("/Personajes");
            return View(personajes);
        }

        // GET: PersonajesController/Details/5
        public async Task<ActionResult> Details(int id)
        {
            var personaje = await personajeApi.SelectById("/Personajes", id.ToString());
            return View(personaje);
        }

        // GET: PersonajesController/Create
        public async Task<ActionResult> Create()
        {
            var peliculas = (await peliculaApi.Select("/Peliculas"))
               ?.Select(
                    p => new SelectListItem { Text = p.Titulo, Value = p.Id.ToString() }
                ).ToList();

            ViewBag.Peliculas = peliculas;

            return View();
        }

        // POST: PersonajesController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Create(Personaje personaje)
        {
            try
            {
                await personajeApi.Insert("/Personajes", personaje);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(personaje);
            }
        }

        // GET: PersonajesController/Edit/5
        public async Task<ActionResult> Edit(int id)
        {
            var personaje = await personajeApi.SelectById("/Personajes", id.ToString());
            var peliculas = (await peliculaApi.Select("/Peliculas"))
              ?.Select(
                   p => new SelectListItem { Text = p.Titulo, Value = p.Id.ToString() }
               ).ToList();

            ViewBag.Peliculas = peliculas;

            return View(personaje);
        }

        // POST: PersonajesController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Edit(int id, Personaje personaje)
        {
            try
            {
                await personajeApi.Update("/Personajes", id.ToString(), personaje);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(personaje);
            }
        }

        // GET: PersonajesController/Delete/5
        public async Task<ActionResult> Delete(int id)
        {
            var personaje = await personajeApi.SelectById("/Personajes", id.ToString());
            return View(personaje);
        }

        // POST: PersonajesController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Delete(int id, Personaje personaje)
        {
            try
            {
                await personajeApi.Delete("/Personajes", id.ToString());
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(personaje);
            }
        }
    }
}
