using System.Net;
using Newtonsoft.Json;

namespace EmpresaUTN.UniversalAPI
{
    public class Crud<T>
    {
        public T[] Select(string url)
        {
            try
            {
                using var api = new WebClient();
                api.Headers.Add("Content-Type", "application/json");

                var json = api.DownloadString(url);
                var data = JsonConvert.DeserializeObject<T[]>(json);

                return data;
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }



        public T SelectById(string url, string id)
        {
            try
            {
                using var api = new WebClient();
                api.Headers.Add("Content-Type", "application/json");

                var json = api.DownloadString($"{url}/{id}");
                var data = JsonConvert.DeserializeObject<T>(json);

                return data;
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }
        public T Insert(string url, T obj)
        {
            try
            {
                using var api = new WebClient();

                api.Headers.Add("Content-Type", "application/json");

                var json = JsonConvert.SerializeObject(obj);
                var resp = api.UploadString(url, "POST", json);

                var data = JsonConvert.DeserializeObject<T>(resp);

                return data;
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }

        public void Update(string url, string id, T obj)
        {
            try
            {
                using var api = new WebClient();

                api.Headers.Add("Content-Type", "application/json");

                var json = JsonConvert.SerializeObject(obj);

                api.UploadString($"{url}/{id}", "PUT", json);
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }

        public void Delete(string url, string id)
        {
            try
            {
                using var api = new WebClient();
                api.Headers.Add("Content-Type", "application/json");
                api.UploadString($"{url}/{id}", "DELETE", "");
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }

    }

}