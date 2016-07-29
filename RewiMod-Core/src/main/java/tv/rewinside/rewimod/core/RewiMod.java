/*
 * RewiMod, a Minecraft Client Enhancement
 * Copyright (C) rewinside.tv <https://rewinside.tv/>
 * Copyright (C) RewiMod team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tv.rewinside.rewimod.core;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.rewinside.rewimod.core.handlers.IGlStateManagerHandler;
import tv.rewinside.rewimod.core.handlers.IGuiHandler;
import tv.rewinside.rewimod.core.handlers.ITextureHandler;
import tv.rewinside.rewimod.core.util.Messages;

public abstract class RewiMod {

	public static final String FINGERPRINT = "7caf764152a9e91657dbbceb44630e90774fa1fd";
	public static final boolean DEACTIVATEABLE = true;
	public static final Logger LOGGER = LogManager.getLogger("RewiMod");

	@Getter private static RewiMod instance;

	@Getter private final Messages messages = new Messages();

	@Getter private String minecraftVersion;

	/**
	 * Constructs a new RewiMod with the default Language loaded
	 */
	protected RewiMod() {
		this.messages.load(null);
	}

	/**
	 * Initializes the Mod
	 *
	 * @param language the selected Game Language
	 * @param mcVersion the current Minecraft Version
	 */
	protected void initialize(String language, String mcVersion) {
		instance = this;
		this.minecraftVersion = mcVersion;

		this.messages.load(language);
		this.registerEvents();

		LOGGER.info("Successfully Initialized " + this.getModId() + this.getVersion());
	}

	/**
	 * Logs a warning, that the Fingerprint is invalid
	 */
	protected void onFingerprintViolation() {
		LOGGER.warn(this.messages.getMessage("startup.signedFail"));
	}

	/**
	 * Registers all Event listeners
	 */
	protected abstract void registerEvents();

	/**
	 * Connects directly to a multiplayer server
	 *
	 * @param host the serverip
	 * @param port the port
	 */
	public abstract void connectToServer(String host, int port);

	/**
	 * Gets the mod-unique identificator
	 *
	 * @return the identificator as String
	 */
	public abstract String getModId();

	/**
	 * Gets the name of the mod
	 *
	 * @return the name as String
	 */
	public abstract String getName();

	/**
	 * Gets the version of the mod
	 *
	 * @return the version as String
	 */
	public abstract String getVersion();

	/**
	 * Gets the handler for handling textures
	 *
	 * @return an implementation of {@link tv.rewinside.rewimod.core.handlers.ITextureHandler}
	 */
	public abstract ITextureHandler getTextureHandler();

	/**
	 * Gets the handler for handling gui
	 *
	 * @return an implementation of {@link tv.rewinside.rewimod.core.handlers.IGuiHandler}
	 */
	public abstract IGuiHandler getGuiHandler();

	/**
	 * Gets the handler for handling the GlStateManager
	 *
	 * @return an implementation of {@link tv.rewinside.rewimod.core.handlers.IGlStateManagerHandler}
	 */
	public abstract IGlStateManagerHandler getGlStateManagerHandler();

}
