Feature: LLamando al endpoint Upload

  Como Qa automatizador
  Quiero ejecutar este reto
  Para validar este endpoint

  @postUpload
  Scenario: Usuario carga una imagen POST
    When ejecuto el endpoint post upload "/Users/macboockprochm3572/Documents/imagen.png"
    Then válido la respuesta del enpoint "File uploaded successfully!"