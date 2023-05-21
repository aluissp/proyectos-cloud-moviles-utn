using AutoMapper;
using BlogPeliculas.API.DTOs;
using BlogPeliculas.Modelos;

namespace BlogPeliculas.API.Utilidades
{
    public class AutoMapperProfiles : Profile
    {
        public AutoMapperProfiles()
        {
            CreateMap<PeliculaCreacionDTO, Pelicula>();
            CreateMap<ComentarioCreacionDTO, Comentario>();
            CreateMap<PersonajeCreacionDTO, Personaje>();

            CreateMap<ComentarioActualizarDTO, Comentario>();
            CreateMap<PersonajeActualizarDTO, Personaje>();
        }
    }
}
