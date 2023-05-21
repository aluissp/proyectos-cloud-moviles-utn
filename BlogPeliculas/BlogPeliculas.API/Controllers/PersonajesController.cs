using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using BlogPeliculas.Modelos;
using AutoMapper;
using BlogPeliculas.API.DTOs;

namespace BlogPeliculas.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PersonajesController : ControllerBase
    {
        private readonly BlogPeliculasContext _context;
        private readonly IMapper mapper;

        public PersonajesController(BlogPeliculasContext context, IMapper mapper)
        {
            _context = context;
            this.mapper = mapper;
        }

        // GET: api/Personajes
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Personaje>>> GetPersonaje()
        {
            if (_context.Personajes == null) return NotFound();

            return await _context.Personajes
                .Include(p => p.Pelicula)
                .ToListAsync();
        }

        // GET: api/Personajes/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Personaje>> GetPersonaje(int id)
        {
            if (_context.Personajes == null) return NotFound();

            var personaje = await _context.Personajes
                .Include(p => p.Pelicula)
                .FirstOrDefaultAsync(p => p.Id == id);

            if (personaje == null) return NotFound();

            return personaje;
        }

        // PUT: api/Personajes/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPersonaje(int id, PersonajeActualizarDTO personajeActualizar)
        {
            var personaje = mapper.Map<Personaje>(personajeActualizar);

            if (id != personaje.Id) return BadRequest();

            _context.Entry(personaje).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PersonajeExists(id))
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

        // POST: api/Personajes
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Personaje>> PostPersonaje(PersonajeCreacionDTO personajeCreacion)
        {
            var personaje = mapper.Map<Personaje>(personajeCreacion);

            if (_context.Personajes == null) return Problem("Entity set 'BlogPeliculasContext.Personaje'  is null.");

            _context.Personajes.Add(personaje);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetPersonaje", new { id = personaje.Id }, personaje);
        }

        // DELETE: api/Personajes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePersonaje(int id)
        {
            if (_context.Personajes == null) return NotFound();

            var personaje = await _context.Personajes.FindAsync(id);

            if (personaje == null) return NotFound();

            _context.Personajes.Remove(personaje);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool PersonajeExists(int id)
        {
            return (_context.Personajes?.Any(e => e.Id == id)).GetValueOrDefault();
        }
    }
}
