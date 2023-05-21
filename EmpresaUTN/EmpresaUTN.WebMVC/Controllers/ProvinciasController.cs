using EmpresaUTN.Modelos;
using EmpresaUTN.UniversalAPI;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace EmpresaUTN.WebMVC.Controllers
{
    public class ProvinciasController : Controller
    {

        private readonly ClientCrud<Provincia> provinciaApi = new("https://localhost:7285/api");
        private readonly ClientCrud<Pais> paisApi = new("https://localhost:7285/api");

        // GET: ProvinciasController
        public async Task<ActionResult> Index()
        {
            var provincias = await provinciaApi.Select("/Provincias");
            return View(provincias);
        }

        // GET: ProvinciasController/Details/5
        public async Task<ActionResult> Details(int id)
        {
            var provincia = await provinciaApi.SelectById("/Provincias", id.ToString());
            return View(provincia);
        }

        // GET: ProvinciasController/Create
        public async Task<ActionResult> Create()
        {
            var paises = (await paisApi.Select("/Pais"))
                ?.Select(p =>
                    new SelectListItem { Text = p.Nombre, Value = p.CodigoPais.ToString() }
                ).ToList();

            ViewBag.Paises = paises;

            return View();
        }

        // POST: ProvinciasController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Create(Provincia provincia)
        {
            try
            {
                await provinciaApi.Insert("/Provincias", provincia);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: ProvinciasController/Edit/5
        public async Task<ActionResult> Edit(int id)
        {
            var paises = (await paisApi.Select("/Pais"))
                ?.Select(p =>
                            new SelectListItem { Text = p.Nombre, Value = p.CodigoPais.ToString() }
                ).ToList();

            ViewBag.Paises = paises;

            var provincia = await provinciaApi.SelectById("/Provincias", id.ToString());
            return View(provincia);
        }

        // POST: ProvinciasController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Edit(int id, Provincia provincia)
        {
            try
            {
                await provinciaApi.Update("/Provincias", id.ToString(), provincia);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(provincia);
            }
        }

        // GET: ProvinciasController/Delete/5
        public async Task<ActionResult> Delete(int id)
        {
            var provincia = await provinciaApi.SelectById("/Provincias", id.ToString());
            return View(provincia);
        }

        // POST: ProvinciasController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Delete(int id, Provincia provincia)
        {
            try
            {
                await provinciaApi.Delete("/Provincias", id.ToString());
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(provincia);
            }
        }
    }
}
