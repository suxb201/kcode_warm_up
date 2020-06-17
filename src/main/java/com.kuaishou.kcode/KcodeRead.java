package com.kuaishou.kcode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class KcodeRead {
    private MappedByteBuffer mapped_byte_buffer = null;
    private FileChannel file_channel = null;
    private final int READ_ONCE_LENGTH = 200000 * 50 * 16;
    private long file_length = 0;
    private long offset = 0;
    char[] ch = new char[50];

    public KcodeRead(InputStream input_stream_) throws IOException {
        FileInputStream input_stream = (FileInputStream) input_stream_;
        file_channel = input_stream.getChannel();
        file_length = file_channel.size();
        mapped_byte_buffer = file_channel.map(FileChannel.MapMode.READ_ONLY, offset, READ_ONCE_LENGTH);
        offset += READ_ONCE_LENGTH;
        mapped_byte_buffer.load();
//        System.out.println(mapped_byte_buffer.isLoaded());
    }

    public String readline() throws IOException {
        int index = -1;
        do {
            index += 1;
            try {
                ch[index] = (char) mapped_byte_buffer.get();
            } catch (BufferUnderflowException e) {
                if (offset == file_length) {
                    return null;
                }
                int read_size = (int) Math.min(READ_ONCE_LENGTH, file_length - offset);
//                System.out.println("read: " + read_size);
                mapped_byte_buffer = file_channel.map(FileChannel.MapMode.READ_ONLY, offset, read_size);
                mapped_byte_buffer.load();
                offset += read_size;
                ch[index] = (char) mapped_byte_buffer.get();
            }
        } while (ch[index] != '\n');
        return String.valueOf(ch, 0, index);
    }
}
