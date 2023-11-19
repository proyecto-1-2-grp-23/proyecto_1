
from ..models.envio import Envio, EnvioSchema
from ..models.respuesta_enviada import CreatedRespuestaEnviadaJsonSchema, RespuestaEnviada, RespuestaEnviadaSchema
from .base_command import BaseCommannd
from ..session import Session
import requests
import json

class CreatePreguntaPorCandidato(BaseCommannd):
    def __init__(self, data, idCandidato):
        self.data = data
        self.idCandidato = idCandidato

    def execute(self):
        idCandidato= self.idCandidato
        print("idCandidato", idCandidato)

        url_candidatos = f'http://127.0.0.1:5001/projects/listar-projects/candidato/{idCandidato}'
        response_candidatos = requests.get(url_candidatos)
        
        if response_candidatos.status_code == 200:
            body_proyecto_candidato = json.loads(response_candidatos.text)
            if(body_proyecto_candidato):
                for proyecto_candidato in body_proyecto_candidato:
                    if(proyecto_candidato['idProyecto']):
                        body: dict = self.data.copy()
                        respuestas_enviadas = body.pop("respuestasEnviadas")
                        posted_data = EnvioSchema(
                            only=(
                                "idProyecto",
                                "idCandidato",
                                "observaciones",
                                )
                        ).load(body)

                        envio = Envio(**posted_data)
                        session = Session()
                        session.add(envio)
                        session.commit()
                        for respuestaEnviada in respuestas_enviadas:
                            posted_data = RespuestaEnviadaSchema(only=("idRespuesta")).load(respuestaEnviada)
                            respuesta_db = RespuestaEnviada(**posted_data, idEnvio=envio.id)
                        session.add(respuesta_db)
                        session.commit()
                        new_envio = CreatedRespuestaEnviadaJsonSchema().dump(envio)
                        session.close()
                        
                        return new_envio

            else:
                return "No existe algún candidato con el id: " + f"{idCandidato}" + "que pertenezca a algún proyecto"
        else:
            return "Problemas con el servicio"
    