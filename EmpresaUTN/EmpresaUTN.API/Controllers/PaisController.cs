using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using EmpresaUTN.Modelos;
using EmpresaUTN.API.DTOs;
using AutoMapper;

namespace EmpresaUTN.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PaisController : ControllerBase
    {
        private readonly DataContext _context;
        private readonly IMapper mapper;

        public PaisController(DataContext context, IMapper mapper)
        {
            _context = context;
            this.mapper = mapper;
        }

        // GET: api/Pais
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Pais>>> GetPaises()
        {
            if (_context.Paises == null) return NotFound();

            return await _context.Paises
                .Include(p => p.Provincias)
                .ThenInclude(pr => pr.Cantones)
                .ToListAsync();
        }

        // GET: api/Pais/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Pais>> GetPais(int id)
        {
            if (_context.Paises == null) return NotFound();

            var pais = await _context.Paises
                .FirstOrDefaultAsync(p => p.CodigoPais == id);

            if (pais == null) return NotFound();

            return pais;
        }

        // PUT: api/Pais/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPais(int id, Pais pais)
        {
            if (id != pais.CodigoPais) return BadRequest();

            _context.Entry(pais).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PaisExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Pais
        [HttpPost]
        public async Task<ActionResult<Pais>> PostPais(CreatePaisDTO createPais)
        {
            if (_context.Paises == null) return Problem("Entity set 'DataContext.Paises'  is null.");

            var pais = mapper.Map<Pais>(createPais);

            _context.Paises.Add(pais);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetPais", new { id = pais.CodigoPais }, pais);
        }

        // DELETE: api/Pais/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePais(int id)
        {
            if (_context.Paises == null)
            {
                return NotFound();
            }
            var pais = await _context.Paises.FindAsync(id);
            if (pais == null)
            {
                return NotFound();
            }

            _context.Paises.Remove(pais);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool PaisExists(int id)
        {
            return (_context.Paises?.Any(e => e.CodigoPais == id)).GetValueOrDefault();
        }
    }
}
