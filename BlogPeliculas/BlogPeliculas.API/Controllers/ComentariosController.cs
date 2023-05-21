using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using BlogPeliculas.Modelos;
using AutoMapper;
using BlogPeliculas.API.DTOs;

namespace BlogPeliculas.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ComentariosController : ControllerBase
    {
        private readonly BlogPeliculasContext _context;
        private readonly IMapper mapper;

        public ComentariosController(BlogPeliculasContext context, IMapper mapper)
        {
            _context = context;
            this.mapper = mapper;
        }

        // GET: api/Comentarios
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Comentario>>> GetComentario()
        {
            if (_context.Comentarios == null) return NotFound();

            return await _context.Comentarios
                .Include(c => c.Pelicula)
                .ToListAsync();
        }

        // GET: api/Comentarios/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Comentario>> GetComentario(int id)
        {
            if (_context.Comentarios == null) return NotFound();

            var comentario = await _context.Comentarios
                .Include(c => c.Pelicula)
                .FirstOrDefaultAsync(c => c.Id == id);

            if (comentario == null) return NotFound();

            return comentario;
        }

        // PUT: api/Comentarios/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutComentario(int id, ComentarioActualizarDTO comentarioActualizar)
        {
            var comentario = mapper.Map<Comentario>(comentarioActualizar);

            if (id != comentario.Id) return BadRequest();

            _context.Entry(comentario).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ComentarioExists(id))
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

        // POST: api/Comentarios
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Comentario>> PostComentario(ComentarioCreacionDTO comentarioCreacion)
        {
            var comentario = mapper.Map<Comentario>(comentarioCreacion);

            if (_context.Comentarios == null) return Problem("Entity set 'BlogPeliculasContext.Comentario'  is null.");

            _context.Comentarios.Add(comentario);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetComentario", new { id = comentario.Id }, comentario);
        }

        // DELETE: api/Comentarios/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteComentario(int id)
        {
            if (_context.Comentarios == null) return NotFound();

            var comentario = await _context.Comentarios.FindAsync(id);

            if (comentario == null) return NotFound();

            _context.Comentarios.Remove(comentario);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool ComentarioExists(int id)
        {
            return (_context.Comentarios?.Any(e => e.Id == id)).GetValueOrDefault();
        }
    }
}
