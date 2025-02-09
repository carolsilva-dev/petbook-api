CREATE TABLE public.foto (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    compartilhada BOOLEAN NOT NULL,
    pet_id UUID NOT NULL,
    usuario_id UUID NOT NULL,
    CONSTRAINT fk_foto_pet FOREIGN KEY (pet_id) REFERENCES public.pet(id) ON DELETE CASCADE,
    CONSTRAINT fk_foto_usuario FOREIGN KEY (usuario_id) REFERENCES public.usuario(id) ON DELETE CASCADE
);
