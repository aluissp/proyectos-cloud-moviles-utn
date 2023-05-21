using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using EmpresaUTN.Modelos;
using AutoMapper;
using EmpresaUTN.API.DTOs;

namespace EmpresaUTN.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CantonesController : ControllerBase
    {
        private readonly DataContext _context;
        private readonly IMapper mapper;

        public CantonesController(DataContext context, IMapper mapper)
        {
            _context = context;
            this.mapper = mapper;
        }

        // GET: api/Cantons
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Canton>>> GetCantones()
        {
            if (_context.Cantones == null) return NotFound();

            return await _context.Cantones
                .Include(c => c.Provincia)
                .ToListAsync();
        }

        // GET: api/Cantons/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Canton>> GetCanton(int id)
        {
            if (_context.Cantones == null) return NotFound();

            var canton = await _context.Cantones
                .Include(c => c.Provincia)
                .FirstOrDefaultAsync(c => c.Id == id);

            if (canton == null) return NotFound();

            return canton;
        }

        // PUT: api/Cantons/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutCanton(int id, UpdateCantonDTO updateCanton)
        {
            var canton = mapper.Map<Canton>(updateCanton);

            if (id != canton?.Id) return BadRequest();

            _context.Entry(canton).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CantonExists(id))
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

        // POST: api/Cantons
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Canton>> PostCanton(CreateCantonDTO createCanton)
        {
            if (_context.Cantones == null)
                return Problem("Entity set 'DataContext.Cantones'  is null.");

            var canton = mapper.Map<Canton>(createCanton);

            _context.Cantones.Add(canton);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetCanton", new { id = canton.Id }, canton);
        }

        // DELETE: api/Cantons/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCanton(int id)
        {
            if (_context.Cantones == null) return NotFound();

            var canton = await _context.Cantones.FindAsync(id);

            if (canton == null) return NotFound();

            _context.Cantones.Remove(canton);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool CantonExists(int id)
        {
            return (_context.Cantones?.Any(e => e.Id == id)).GetValueOrDefault();
        }
    }
}
