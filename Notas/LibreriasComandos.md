# Listado de librerías

### Para instalar Sqlite
```bash
Install-Package Microsoft.EntityFrameworkCore
Install-Package Microsoft.EntityFrameworkCore.Sqlite
Install-Package Microsoft.EntityFrameworkCore.Tools
Install-Package SQLitePCLRaw.bundle_e_sqlite3
Install-Package System.ComponentModel.Annotations
```

* Config: "Data Source=.\\empresautn.db"

### Para serializar y deserializar objetos
```bash
Install-Package Newtonsoft.Json
```

# Comandos útiles

### Migraciones
```bash
Add-Migration [nombre_de_la_migración]
Update-Database
Remove-Migration
```

# Extra
Para realizar mapeos: AutoMapper
