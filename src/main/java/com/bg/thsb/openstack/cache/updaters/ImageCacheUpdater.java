package com.bg.thsb.openstack.cache.updaters;

import com.bg.thsb.dal.Dao;
import com.bg.thsb.openstack.OSClientWrapper;
import com.bg.thsb.openstack.model.entities.Image;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ImageCacheUpdater
 *
 */
@Service
public class ImageCacheUpdater extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(ImageCacheUpdater.class);

	Dao<Image> dao = Dao.of(Image.class);

	@Override
	public void run() {
		final List<? extends org.openstack4j.model.compute.Image> list = OSClientWrapper.getOs().compute().images().list();

		ModelMapper modelMapper = new ModelMapper();

		list.forEach(sourceImage -> {
			final Image destImage = modelMapper.map(sourceImage, Image.class);
			dao.put(destImage);
		});
		logger.info(this.getClass().getName() + " refreshed");
	}
}
