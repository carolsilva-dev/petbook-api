CREATE TABLE public.postagem (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    data_postagem TIMESTAMP NOT NULL,
    usuario_id UUID NOT NULL,
    foto_id UUID NOT NULL,
    pet_id UUID NOT NULL,
    CONSTRAINT fk_postagem_usuario FOREIGN KEY (usuario_id) REFERENCES public.usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_postagem_foto FOREIGN KEY (foto_id) REFERENCES public.foto(id) ON DELETE CASCADE,
    CONSTRAINT fk_postagem_pet FOREIGN KEY (pet_id) REFERENCES public.pet(id) ON DELETE CASCADE
);
