using EmpresaUTN.Modelos;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace EmpresaUTN.API.Data.Settings
{
    public class PaisConfig : IEntityTypeConfiguration<Pais>
    {
        public void Configure(EntityTypeBuilder<Pais> builder)
        {
            builder.HasKey(pais => pais.CodigoPais);
        }
    }
}
