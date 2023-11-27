from ..models.envio import Envio, EnvioSchema
from ..models.respuesta_enviada import (
    CreatedRespuestaEnviadaJsonSchema,
    RespuestaEnviada,
    RespuestaEnviadaSchema,
)
from .base_command import BaseCommannd
from ..session import Session
import requests
import json


class CreatePreguntaPorCandidato(BaseCommannd):
    def __init__(self, data):
        self.data = data

    def execute(self):
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
        new_envio = CreatedRespuestaEnviadaJsonSchema().dump(envio)
        session.close()

        for respuestaEnviada in respuestas_enviadas:
            print("respuestaEnviada")
            print(respuestaEnviada)
            posted_data = RespuestaEnviadaSchema(
                only=(["idRespuesta", "correcta"])
            ).load(respuestaEnviada)
            respuesta_db = RespuestaEnviada(**posted_data, idEnvio=envio.id)
            session.add(respuesta_db)
        session.commit()

        session.close()

        return new_envio
