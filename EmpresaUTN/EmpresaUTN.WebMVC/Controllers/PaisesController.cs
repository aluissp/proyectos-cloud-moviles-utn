using EmpresaUTN.Modelos;
using EmpresaUTN.UniversalAPI;
using Microsoft.AspNetCore.Mvc;

namespace EmpresaUTN.WebMVC.Controllers
{
    public class PaisesController : Controller
    {

        private readonly ClientCrud<Pais> paisesApi = new("https://localhost:7285/api");


        // GET: PaisesController
        public async Task<IActionResult> Index()
        {
            var paises = await paisesApi.Select("/Pais");

            return View(paises);
        }

        // GET: PaisesController/Details/5
        public async Task<IActionResult> Details(int id)
        {
            var pais = await paisesApi.SelectById("/Pais", id.ToString());

            return View(pais);
        }

        // GET: PaisesController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: PaisesController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create(Pais pais)
        {
            try
            {
                await paisesApi.Insert("/Pais", pais);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(pais);
            }
        }

        // GET: PaisesController/Edit/5
        public async Task<IActionResult> Edit(int id)
        {
            var pais = await paisesApi.SelectById("/Pais", id.ToString());

            return View(pais);
        }

        // POST: PaisesController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, Pais pais)
        {
            try
            {
                await paisesApi.Update("/Pais", id.ToString(), pais);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(pais);
            }
        }

        // GET: PaisesController/Delete/5
        public async Task<IActionResult> Delete(int id)
        {
            var pais = await paisesApi.Delete("/Pais", id.ToString());

            return View(pais);
        }

        // POST: PaisesController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Delete(int id, Pais pais)
        {
            try
            {
                await paisesApi.Delete("/Pais", id.ToString());
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View(pais);
            }
        }
    }
}
