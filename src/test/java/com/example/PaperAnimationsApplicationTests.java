package com.example;

import com.example.imgManipulation.defaultImplementations.SimpleInterlacer;
import com.example.imgManipulation.defaultImplementations.SliceWriter;
import com.example.imgManipulation.interfaces.Image;
import com.example.imgManipulation.interfaces.Pixel;
import com.example.imgManipulation.interfaces.SparseStripe;
import com.example.imgManipulation.interfaces.Stripe;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.imageio.ImageIO.write;

public class PaperAnimationsApplicationTests {

	@Test
	public void contextLoads() throws IOException {
		final int expectedRGB=200;
		final int width=100;
		final  int height = 10;
		List<Stripe> stripes = new ArrayList<>();
		List<Pixel> whiteRow = new ArrayList<>();
		Pixel whitePixel = new Pixel(expectedRGB);
		for(int i = 0; i<10;i++){
			whiteRow.add(whitePixel);
		}
		SparseStripe stripe = new SparseStripe();
		for (int i = 0; i<height;i++){
			stripe.addOnTop(whiteRow);
		}

		for (int i = 0; i < 10; i++) {
			stripes.add(stripe);
		}

		SliceWriter writer = new SliceWriter();

//		Image bufferedImage = new BufferedImage(100, 10, BufferedImage.TYPE_INT_ARGB);
//		writer.writeToBuffer(bufferedImage,stripes);
//		String filename="randomFileName.png";
//		write(bufferedImage, "png", new File(filename));
//		BufferedImage img = ImageIO.read(new File(filename));
//
//		Assert.assertEquals("height",img.getHeight(),height);
//		Assert.assertEquals("height",img.getWidth(),width);
//		for (int i = 0; i < img.getWidth(); i++) {
//			for (int j = 0; j < img.getHeight(); j++) {
//				Assert.assertEquals("pixel ("+i+","+j+")",expectedRGB,img.getRGB(i,j));
//			}
//		}



	}

}
