using System.Net;
using System.Net.Http.Json;


namespace EmpresaUTN.UniversalAPI
{
    public class ClientCrud<T>
    {
        private static readonly HttpClient httpClient = new();
        private readonly string baseUrl;

        public ClientCrud(string baseUrl)
        {
            this.baseUrl = baseUrl;
        }

        //public async Task Select(string url)
        public async Task<T[]?> Select(string url)
        {
            try
            {

                //var resp = await httpClient.GetAsync(baseUrl + url);

                //var jsonResponse = await resp.Content.ReadAsStringAsync();
                //Console.WriteLine($"{jsonResponse}\n");
                //var data = resp?.ToList();

                //data?.ForEach(item => Console.WriteLine(item));

                return await httpClient.GetFromJsonAsync<T[]>(baseUrl + url);
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }



        public async Task<T?> SelectById(string url, string id)
        {
            try
            {
                return await httpClient.GetFromJsonAsync<T>($"{baseUrl}{url}/{id}");
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }

        public async Task<T?> Insert(string url, T data)
        {
            try
            {
                using HttpResponseMessage res = await httpClient.PostAsJsonAsync($"{baseUrl}{url}", data);

                if (res.StatusCode != HttpStatusCode.Created)
                    throw new Exception("No se ha podido insertar el registro");

                return await res.Content.ReadFromJsonAsync<T>();
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }

        public async Task Update(string url, string id, T data)
        {
            try
            {
                using HttpResponseMessage res = await httpClient.PutAsJsonAsync($"{baseUrl}{url}/{id}", data);

                //return await res.Content.ReadFromJsonAsync<T>();
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }

        public async Task<bool> Delete(string url, string id)
        {
            try
            {
                using HttpResponseMessage res = await httpClient.DeleteAsync($"{baseUrl}{url}/{id}");
                return res.IsSuccessStatusCode;
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }
    }

}