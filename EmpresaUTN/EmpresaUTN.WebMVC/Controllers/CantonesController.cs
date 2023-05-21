using EmpresaUTN.Modelos;
using EmpresaUTN.UniversalAPI;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace EmpresaUTN.WebMVC.Controllers
{
    public class CantonesController : Controller
    {
        private readonly ClientCrud<Canton> cantonApi = new("https://localhost:7285/api");
        private readonly ClientCrud<Provincia> provinciaApi = new("https://localhost:7285/api");

        // GET: CantonesController
        public async Task<ActionResult> Index()
        {
            var cantones = await cantonApi.Select("/Cantones");
            return View(cantones);
        }

        // GET: CantonesController/Details/5
        public async Task<ActionResult> Details(int id)
        {
            var canton = await cantonApi.SelectById("/Cantones", id.ToString());
            return View(canton);
        }

        // GET: CantonesController/Create
        public async Task<ActionResult> Create()
        {
            var provincias = (await provinciaApi.Select("/Provincias"))
                ?.Select(p =>
                    new SelectListItem { Text = p.Nombre, Value = p.Id.ToString() }
                ).ToList();

            ViewBag.Provincias = provincias;

            return View();
        }

        // POST: CantonesController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Create(Canton canton)
        {
            try
            {
                await cantonApi.Insert("/Cantones", canton);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(canton);
            }
        }

        // GET: CantonesController/Edit/5
        public async Task<ActionResult> Edit(int id)
        {
            var provincias = (await provinciaApi.Select("/Provincias"))
                ?.Select(p =>
                    new SelectListItem { Text = p.Nombre, Value = p.Id.ToString() }
                ).ToList();

            ViewBag.Provincias = provincias;

            var canton = await cantonApi.SelectById("/Cantones", id.ToString());

            return View(canton);
        }

        // POST: CantonesController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Edit(int id, Canton canton)
        {
            try
            {
                await cantonApi.Update("/Cantones", id.ToString(), canton);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(canton);
            }
        }

        // GET: CantonesController/Delete/5
        public async Task<ActionResult> Delete(int id)
        {
            var canton = await cantonApi.SelectById("/Cantones", id.ToString());
            return View(canton);
        }

        // POST: CantonesController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Delete(int id, Canton canton)
        {
            try
            {
                await cantonApi.Delete("/Cantones", id.ToString());
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(canton);
            }
        }
    }
}
