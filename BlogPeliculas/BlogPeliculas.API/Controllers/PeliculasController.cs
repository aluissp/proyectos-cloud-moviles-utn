using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using BlogPeliculas.Modelos;
using AutoMapper;
using BlogPeliculas.API.DTOs;

namespace BlogPeliculas.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PeliculasController : ControllerBase
    {
        private readonly BlogPeliculasContext _context;
        private readonly IMapper mapper;

        public PeliculasController(BlogPeliculasContext context, IMapper mapper)
        {
            _context = context;
            this.mapper = mapper;
        }

        // GET: api/Peliculas
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Pelicula>>> GetPelicula()
        {
            if (_context.Peliculas == null) return NotFound();

            return await _context.Peliculas
                .Include(p => p.Personjes)
                .Include(p => p.Comentarios)
                .ToListAsync();
        }

        // GET: api/Peliculas/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Pelicula>> GetPelicula(int id)
        {
            if (_context.Peliculas == null) return NotFound();

            var pelicula = await _context.Peliculas
                .Include(p => p.Personjes)
                .Include(p => p.Comentarios)
                .FirstOrDefaultAsync(p => p.Id == id);

            if (pelicula == null) return NotFound();

            return pelicula;
        }

        // PUT: api/Peliculas/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPelicula(int id, Pelicula pelicula)
        {
            if (id != pelicula.Id) return BadRequest();

            _context.Entry(pelicula).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PeliculaExists(id))
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

        // POST: api/Peliculas
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Pelicula>> PostPelicula(PeliculaCreacionDTO peliculaCreacion)
        {
            var pelicula = mapper.Map<Pelicula>(peliculaCreacion);

            if (_context.Peliculas == null) return Problem("Entity set 'BlogPeliculasContext.Pelicula'  is null.");

            _context.Peliculas.Add(pelicula);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetPelicula", new { id = pelicula.Id }, pelicula);
        }

        // DELETE: api/Peliculas/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePelicula(int id)
        {
            if (_context.Peliculas == null) return NotFound();

            var pelicula = await _context.Peliculas.FindAsync(id);

            if (pelicula == null) return NotFound();

            _context.Peliculas.Remove(pelicula);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool PeliculaExists(int id)
        {
            return (_context.Peliculas?.Any(e => e.Id == id)).GetValueOrDefault();
        }
    }
}
