using Microsoft.AspNetCore.Mvc;
using AutoMapper;
using EmpresaUTN.Modelos;
using Microsoft.EntityFrameworkCore;

namespace EmpresaUTN.API.Controllers.DataAccesLayer
{
    [Route("api/pais")]
    [ApiController]
    public class BLPaisController : ControllerBase
    {
        private readonly DataContext context;
        private readonly IMapper mapper;

        public BLPaisController(DataContext context, IMapper mapper)
        {
            this.context = context;
            this.mapper = mapper;
        }

        [Route("AreaTotal")]
        [HttpGet]
        public async Task<ActionResult> AreaTotal()
        {
            if (context.Paises == null) return NotFound();

            var areaTotal = await context.Paises.Select(
                pais => new
                {
                    pais.Nombre,
                    AreaTotal = pais.Provincias.Sum(prov => prov.Area)
                })
                .ToListAsync();

            return Ok(areaTotal);
        }
    }
}
