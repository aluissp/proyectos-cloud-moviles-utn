using BlogPeliculas.Modelos;
using BlogPeliculas.UniversalAPI;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace BlogPeliculas.WebMVC.Controllers
{
    public class ComentariosController : Controller
    {
        private readonly ClientCrud<Comentario> comentarioApi = new("https://localhost:7093/api");
        private readonly ClientCrud<Pelicula> peliculaApi = new("https://localhost:7093/api");

        // GET: ComentariosController
        public async Task<ActionResult> Index()
        {
            var comentarios = await comentarioApi.Select("/Comentarios");
            return View(comentarios);
        }

        // GET: ComentariosController/Details/5
        public async Task<ActionResult> Details(int id)
        {
            var comentario = await comentarioApi.SelectById("/Comentarios", id.ToString());
            return View(comentario);
        }

        // GET: ComentariosController/Create
        public async Task<ActionResult> Create()
        {
            var peliculas = (await peliculaApi.Select("/Peliculas"))
                            ?.Select(
                                p => new SelectListItem { Text = p.Titulo, Value = p.Id.ToString() }
                            ).ToList();

            ViewBag.Peliculas = peliculas;

            return View();
        }

        // POST: ComentariosController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Create(Comentario comentario)
        {
            try
            {
                await comentarioApi.Insert("/Comentarios", comentario);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(comentario);
            }
        }

        // GET: ComentariosController/Edit/5
        public async Task<ActionResult> Edit(int id)
        {
            var comentario = await comentarioApi.SelectById("/Comentarios", id.ToString());
            var peliculas = (await peliculaApi.Select("/Peliculas"))
                ?.Select(
                    p => new SelectListItem { Text = p.Titulo, Value = p.Id.ToString() }
                ).ToList();

            ViewBag.Peliculas = peliculas;

            return View(comentario);
        }

        // POST: ComentariosController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Edit(int id, Comentario comentario)
        {
            try
            {
                await comentarioApi.Update("/Comentarios", id.ToString(), comentario);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(comentario);
            }
        }

        // GET: ComentariosController/Delete/5
        public async Task<ActionResult> Delete(int id)
        {
            var comentario = await comentarioApi.SelectById("/Comentarios", id.ToString());
            return View(comentario);
        }

        // POST: ComentariosController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Delete(int id, Comentario comentario)
        {
            try
            {
                await comentarioApi.Delete("/Comentarios", id.ToString());
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(comentario);
            }
        }
    }
}
