using AutoMapper;
using EmpresaUTN.API.DTOs;
using EmpresaUTN.Modelos;

namespace EmpresaUTN.API.Utilities
{
    public class AutoMapperProfiles : Profile
    {
        public AutoMapperProfiles()
        {
            CreateMap<CreatePaisDTO, Pais>();
            CreateMap<CreateProvinciaDTO, Provincia>();
            CreateMap<CreateCantonDTO, Canton>();
        }
    }
}
